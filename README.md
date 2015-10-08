# dispatcher

Launches the appropriate worker from an uberjar

## Usage
In the resources path:
* Create a file entry-points.edn so that it's resource path is `/entry-points.edn`
  The convention will be to ensure that project.clj has the line `:resource-paths ["src/resource"]`
  then place the entry-points.edn file there. This will ensure that there is a constant place where
  developers can look to find the entry points.

In project.clj:
* Set `:main` to `dispatcher.core`
* Add dispatcher as a dependency

    $ java -jar some-service-0.1.0-standalone.jar <entry-point> [args]

## Sample entry-points.edn
    {"web" {:nspace weather-service.web
            :func -main}
     "rain" {:nspace worker.core
             :func -main
             :args ["weather-service.workers" "enqueue-email"]}
     "mrms-ingest" {:nspace weather-service.worker.mrms-ingest
                    :func -main}}
