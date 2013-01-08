package com.ejrav.mi.config

import com.fasterxml.jackson.annotation.JsonProperty

case class Process(name: String, 
    @JsonProperty("output_collection") outputCollection: String, 
    @JsonProperty("output_field") outputField: String, 
    parameters: Map[String, String])