(ns use-buttle.core
  (:gen-class)
  (:require [buttle.proxy :as proxy])
  (:use buttle.driver-manager
        clojure.pprint))

(def -ns *ns*)

(defn invoke-with-logging [the-method target-obj the-args]
  {:pre [(or (println (format "%s: INVOKE %s" -ns
                              (pr-str [the-method target-obj (into [] the-args)]))) true)]
   :post [(or (println (format "%s: RETURN %s --> %s" -ns
                               (pr-str [the-method target-obj (into [] the-args)]) (pr-str %))) true)]}
  (try
    (proxy/handle-default the-method target-obj the-args)
    (catch Throwable t
      (println (format "%s: THROW %s : %s" -ns
                       (pr-str [the-method target-obj (into [] the-args)]) (pr-str t)))
      (throw t))))

#_ ;; prints all/any invocation
(defmethod proxy/handle :default [the-method target-obj the-args]
  (invoke-with-logging the-method target-obj the-args))

(proxy/def-handle [java.sql.Driver :buttle/default] [the-method target-obj the-args]
  (invoke-with-logging the-method target-obj the-args))

(proxy/def-handle [java.sql.Connection :buttle/default] [the-method target-obj the-args]
  (invoke-with-logging the-method target-obj the-args))

(proxy/def-handle [java.sql.Statement :buttle/default] [the-method target-obj the-args]
  (invoke-with-logging the-method target-obj the-args))

(defn select-pg-catalog-tables [target-url user password]
  (doall (-> (get-connection (format "jdbc:buttle:{:target-url \"%s\"}" target-url) user password)
             .createStatement
             (.executeQuery "select * from pg_catalog.pg_tables where schemaname = 'pg_catalog'")
             (resultset-seq))))

(defn -main [& [target-url user password]]
  (pprint (select-pg-catalog-tables target-url user password)))
