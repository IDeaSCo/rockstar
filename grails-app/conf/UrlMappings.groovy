class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

        "/trophy"{
            action = [POST:"save"]
          }
		"/"(controller:"/leader", action:"index")
        "/index"(controller:"/leader", action:"index")
		"500"(view:'error.gsp')
	}
}
