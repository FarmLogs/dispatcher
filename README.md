# dispatcher

Launches the appropriate worker from an uberjar

## Usage
In the resources directory:
* Create a file entry-points.edn

In project.clj:
* Set `:main` to `dispatcher.core`
* Add dispatcher as a dependency

    $ java -jar some-service-0.1.0-standalone.jar entry-point [args]

## Sample entry-points.edn
    {"web" {:nspace weather-service.web
            :func -main}
     "rain" {:nspace worker.core
             :func -main
             :args ["weather-service.workers" "enqueue-email"]}
     "mrms-ingest" {:nspace weather-service.worker.mrms-ingest
                    :func -main}}
