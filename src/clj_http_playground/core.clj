(ns clj-http-playground.core
  (:require [clj-http.client :as http]))

;; get request and parse response body as json:
(http/get "https://api.forecast.io/forecast/f974f5244163e99cb38e48cc19627a55/37.8267,-122.423"
  {:as :json})

;; get request and parse response body as json but leave keys as strings:
(http/get "https://api.forecast.io/forecast/f974f5244163e99cb38e48cc19627a55/37.8267,-122.423"
  {:as :json-string-keys})

;; get the body as a stream
(http/get "https://api.forecast.io/forecast/f974f5244163e99cb38e48cc19627a55/37.8267,-122.423"
  {:as :stream})

;; post and set the content type of the body
(http/post "https://api.forecast.io/forecast/f974f5244163e99cb38e48cc19627a55/37.8267,-122.423"
  {:content-type :json
   :form-params {:name "Eric"}
   :as :json})
