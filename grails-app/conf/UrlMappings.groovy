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
		"/"(view:"/index")
                "/index"(view:"/index")
		"500"(view:'/index')
	}
}
