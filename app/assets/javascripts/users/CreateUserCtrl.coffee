class CreateUserCtrl


  constructor: (@$log, @$location, @$routeParams, @UserService, @$scope) ->
    @$log.debug "constructing CreateUserController"
    @user = {}
    @doUpdate = false
    @getUser(@$routeParams.uuid)

  getUser: (uuid) ->
    @doUpdate = true if uuid
    if @doUpdate
      @$log.debug "getUser(#{uuid})"

      @UserService.getUser(uuid)
      .then(
        (data) =>
          @$log.debug "Promise returned #{angular.toJson(data, true)} User"
          @user = data
      ,
        (error) =>
          @$log.error "Unable to get User: #{error}"
      )

  addLink: (uuid) ->
    @$log.debug "addLink()"
    @user.active = true
    @UserService.addLink(uuid, @$scope.formdata)
    .then(
      (data) =>
        @$log.debug "Promise returned #{data} User"
        @user = data
        @$location.path("/")
    ,
      (error) =>
        @$log.error "Unable to add link to user: #{error}"
    )

  updateUser: () ->
    @UserService.updateUser(@user)
    .then(
      (data) =>
        @$log.debug "Promise returned #{data} User"
        @user = data
        @$location.path("/")
    ,
      (error) =>
        @$log.error "Unable to update User: #{error}"
    )


  deleteUser: (uuid) ->
    @$log.debug "deleteUser(#{uuid})"

    @UserService.deleteUser(uuid)
    .then(
      (data) =>
        @$log.debug "Promise returned #{angular.toJson(data, true)} User"
    ,
      (error) =>
        @$log.error "Unable to delete User: #{error}"
    )


  createUser: () ->
    @$log.debug "createUser()"
    @user.active = true
    @UserService.createUser(@user)
    .then(
      (data) =>
        @$log.debug "Promise returned #{data} User"
        @user = data
        @$location.path("/")
    ,
      (error) =>
        @$log.error "Unable to create User: #{error}"
    )

controllersModule.controller('CreateUserCtrl', CreateUserCtrl)