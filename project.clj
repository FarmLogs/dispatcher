(defproject com.farmlogs/dispatcher "1.0.1"
  :description "Library to start specific workers in a jar"
  :min-lein-version "2.0.0"
  :plugins [[s3-wagon-private "1.2.0"]]
  :repositories {"farmlogs-internal" {:url "s3p://fl-maven-repo/mvn"
                                      :username ~(System/getenv "AMAZON_KEY")
                                      :passphrase ~(System/getenv "AMAZON_SECRET")}}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/tools.logging "0.3.1"]]
  :main dispatcher.core
  :profiles {:uberjar {:aot :all}})
