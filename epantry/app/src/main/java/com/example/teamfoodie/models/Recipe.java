package com.example.teamfoodie.models;

public class Recipe {
   

        private String recipeName;

        // Image name (Without extension)
        private String recipePicName;
        private String description;
        private String url;



    public Recipe(String recipeName, String recipePicName, String description, String url) {
            this.recipeName= recipeName;
            this.recipePicName= recipePicName;
            this.description= description;
            this.url= url;
        }



    public String getdescription() {
            return description;
        }

        public void setdescription(String description) {
            this.description = description;
        }

        public String getrecipeName() {
            return recipeName;
        }

        public void setrecipeName(String recipeName) {
            this.recipeName = recipeName;
        }

        public String getrecipePicName() {
            return recipePicName;
        }

        public void setrecipePicName(String recipePicName) {
            this.recipePicName = recipePicName;
        }

        public String getUrl() { return url; }

        public void setUrl(String url) { this.url = url; }


        @Override
        public String toString()  {
            return this.recipeName;
        }
    }
