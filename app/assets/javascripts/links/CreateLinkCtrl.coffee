
class CreateLinkCtrl

    constructor: (@$log, @$location,  @LinkService) ->
        @$log.debug "constructing CreateLinkController"
        @link = {}

    createLink: () ->
        @$log.debug "createLink()"
        @LinkService.createLink(@link)
        .then(
            (data) =>
                @$log.debug "Promise returned #{data} Link"
                @link = data
                @$location.path("/")
            ,
            (error) =>
                @$log.error "Unable to create Link: #{error}"
            )

controllersModule.controller('CreateLinkCtrl', CreateLinkCtrl)