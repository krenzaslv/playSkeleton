
class LinkService

    @headers = {'Accept': 'application/json', 'Content-Type': 'application/json'}
    @defaultConfig = { headers: @headers }

    constructor: (@$log, @$http, @$q) ->
        @$log.debug "constructing LinkService"

    listLinks: () ->
        @$log.debug "listLinks()"
        deferred = @$q.defer()

        @$http.get("/links")
        .success((data, status, headers) =>
                @$log.info("Successfully listed Links - status #{status}")
                deferred.resolve(data)
            )
        .error((data, status, headers) =>
                @$log.error("Failed to list Links - status #{status}")
                deferred.reject(data);
            )
        deferred.promise

    createLink: (link) ->
        @$log.debug "createLink #{angular.toJson(link, true)}"
        deferred = @$q.defer()

        @$http.post('/link', link)
        .success((data, status, headers) =>
                @$log.info("Successfully created Link - status #{status}")
                deferred.resolve(data)
            )
        .error((data, status, headers) =>
                @$log.error("Failed to create Link - status #{status}")
                deferred.reject(data);
            )
        deferred.promise

servicesModule.service('LinkService', LinkService)