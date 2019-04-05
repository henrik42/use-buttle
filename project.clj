(defproject use-buttle "0.1.0-SNAPSHOT"
  :description "A simple usage example for Buttle https://github.com/henrik42/buttle"
  :url "https://github.com/henrik42/use-buttle"
  :dependencies [[buttle "1.0.0"]
                 [org.postgresql/postgresql "9.4.1212"]]
  :main use-buttle.core
  :profiles {:uberjar {:aot :all}})
