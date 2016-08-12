(ns clj-http-playground.core
  (:require [clj-http.client :as http]))

(def response (http/get "http://lispcast.com"
                {:debug? true}))

(:status response)
(:server (:headers response))
(type (:body response))

