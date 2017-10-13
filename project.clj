(defproject com.farmlogs/dispatcher "1.0.4-SNAPSHOT"
  :description "Library to start specific workers in a jar"
  :url "https://github.com/FarmLogs/dispatcher"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :deploy-repositories {"releases" :clojars}
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.8.0"]]}})
