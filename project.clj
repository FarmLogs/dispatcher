(defproject dispatcher "0.1.0-SNAPSHOT"
  :description "Library to start specific workers in a jar"
  :min-lein-version "2.0.0"
  :plugins [[s3-wagon-private "1.1.2"]]
  :repositories {"farmlogs-internal" {:url "s3p://fl-maven-repo/mvn"
                                      :username ~(System/getenv "AMAZON_KEY")
                                      :passphrase ~(System/getenv "AMAZON_SECRET")}}
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :main dispatcher.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})