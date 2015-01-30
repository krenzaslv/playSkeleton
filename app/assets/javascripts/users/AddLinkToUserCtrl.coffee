
class AddLinkToUserCtrl

    constructor: (@$log, @$location,  @UserService) ->
        @$log.debug "constructing AddLinkToUserController"
        @userId = {}
        @url = {}

  ###  addLink: () ->
        @$log.debug "addLink()"
        @user.active = true
        @UserService.addLink(@userId, @url)
        .then(
            (data) =>
                @$log.debug "Promise returned #{data} User"
                @user = data
                @$location.path("/")
            ,
            (error) =>
                @$log.error "Unable to add link to user: #{error}"
            )
###
controllersModule.controller('AddLinkToUserCtrl', AddLinkToUserCtrl)