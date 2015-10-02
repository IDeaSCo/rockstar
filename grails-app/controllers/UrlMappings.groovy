class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/trophy"{
            action = [POST:"save"]
          }
	"/" (controller:"/leader", action:"index")
        "/index" (controller:"/leader", action:"index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
