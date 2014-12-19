
class LinkCtrl

    constructor: (@$log, @LinkService) ->
        @$log.debug "constructing LinkController"
        @links = []
        @getAllLinks()

    getAllLinks: () ->
        @$log.debug "getAllLinks()"

        @LinkService.listLinks()
        .then(
            (data) =>
                @$log.debug "Promise returned #{data.length} Links"
                @links = data
            ,
            (error) =>
                @$log.error "Unable to get Links: #{error}"
            )


controllersModule.controller('LinkCtrl', LinkCtrl)