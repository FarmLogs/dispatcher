(defproject com.farmlogs/dispatcher "1.0.3-SNAPSHOT"
  :description "Library to start specific workers in a jar"
  :min-lein-version "2.0.0"
  :plugins [[s3-wagon-private "1.2.0"]]
  :repositories {"releases" {:url        "s3p://fl-maven-repo/mvn"
                             :username   :env/AMAZON_KEY
                             :passphrase :env/AMAZON_SECRET}}
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.8.0"]]}})
