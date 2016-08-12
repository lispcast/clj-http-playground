(ns clj-http-playground.core
  (:require [clj-http.client :as http]
            [clj-http.conn-mgr :as conn]))

;; timeout
(http/get "http://lispcast.com/"
  {:conn-timeout 1000
   :socket-timeout 1000})

;; exceptions
(http/get "http://lispcast.com/fdslkfjsdlkfj"
  {:conn-timeout 1000
   :socket-timeout 1000
   :throw-exceptions false})

;; Basic Auth
(http/get "http://lispcast.com/"
  {:basic-auth ["username" "password"]})

;; redirects
(http/get "http://lispcast.com/"
  {:max-redirects 0})

;; ssl insecurity
(http/get "https://lispcast.com/"
  {:insecure? true})

;; connection pool
(def connection-pool (conn/make-reusable-conn-manager {:threads 1 :timeout 4}))

(dotimes [x 1000]
  (http/get "http://lispcast.com/" {:connection-manager connection-pool}))

(conn/shutdown-manager connection-pool)
