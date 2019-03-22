(defproject use-buttle "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [buttle "0.1.1-SNAPSHOT"]
                 [org.postgresql/postgresql "9.4.1212"]]
  :plugins [[lein-swank "1.4.5"]]
  :main ^:skip-aot use-buttle.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})