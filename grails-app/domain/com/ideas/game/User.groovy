package com.ideas.game

class User {

    int id;
    String firstName
    String lastName
    String email
    String employeeId 
    Department department
    String title
    int trophies
    String accountName
    
    static constraints = {
        email unique:true
        employeeId unique:true
        firstName   size:1..60
        lastName   size:1..60
        email   size:1..120
    }

    static mapping = {
        department lazy: false
    }

    String getName(){
        return firstName+" "+lastName;
    }

    boolean isEligibleToGrantMoreOrLessThanOneStars(){
        if( title.toLowerCase().indexOf('manager')>=0 || title.toLowerCase().indexOf('director')>=0 || title.toLowerCase().indexOf('chief')>=0 || title.toLowerCase().indexOf('president')>=0){
            return true;
        }
        return false;
    }

    String getUserBadge(User user) {
        String badge = null;
        if(trophies > 50000){
            badge = "Sun"
        } else if(trophies > 25000){
            badge = "Jupiter"
        } else if(trophies > 20000){
            badge = "Saturn"
        }else if(trophies > 15000){
            badge = "Mars"
        }else if(trophies > 10000){
            badge = "Earth"
        }else if(trophies > 5000){
            badge = "Venus"
        }else if(trophies > 1000){
            badge = "Mercury"
        }else if(trophies > 500){
            badge = "Moon"
        }
        
        return badge ;
    }

    String getUserImage(){
        return "http://sww.sas.com/sww/tools/phonelist/photos/"+accountName.toLowerCase()+".jpg";
    }
    String getNoImage(){
        return "http://sww.sas.com/iss-images/nophoto.jpg";
    }
}
