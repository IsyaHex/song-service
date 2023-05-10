package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "save song metadata"

    request {
        url "/songs"
        method POST()
        headers {
            contentType applicationJson()
        }
        body(
                name: "test",
                artist: "test-artist",
                album: "test-album",
                length: "99999",
                year: "99999",
                resourceId: "999"
        )
    }

    response {
        status CREATED()
        headers {
            contentType applicationJson()
        }
    }
}