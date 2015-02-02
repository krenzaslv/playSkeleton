class UserService

  @headers = {'Accept': 'application/json', 'Content-Type': 'application/json'}
  @defaultConfig = {headers: @headers}

  constructor: (@$log, @$http, @$q) ->
    @$log.debug "constructing UserService"

  listUsers: () ->
    @$log.debug "listUsers()"
    deferred = @$q.defer()

    @$http.get("/users")
    .success((data, status, headers) =>
      @$log.info("Successfully listed Users - status #{status}")
      deferred.resolve(data)
    )
    .error((data, status, headers) =>
      @$log.error("Failed to list Users - status #{status}")
      deferred.reject(data);
    )
    deferred.promise

  createUser: (user) ->
    @$log.debug "createUser #{angular.toJson(user, true)}"
    deferred = @$q.defer()

    @$http.post('/user', user)
    .success((data, status, headers) =>
      @$log.info("Successfully created User - status #{status}")
      deferred.resolve(data)
    )
    .error((data, status, headers) =>
      @$log.error("Failed to create user - status #{status}")
      deferred.reject(data);
    )
    deferred.promise

  getUser: (uuid) ->
    @$log.debug "getUser()"
    deferred = @$q.defer()

    @$http.get("/user/#{uuid}")
    .success((data, status, headers) =>
      @$log.info("Successfully retrieve User - status #{status}")
      deferred.resolve(data)
    )
    .error((data, status, headers) =>
      @$log.error("Failed to retrieve User - status #{status}")
      deferred.reject(data);
    )
    deferred.promise

  addLink: (userId, url) ->
    @$log.debug "add Link  #{angular.toJson(url, true)} to userid " + userId
    deferred = @$q.defer()

    @$http.post('/user/addLink/' + userId, url)
    .success((data, status, headers) =>
      @$log.info("Successfully added Link to User - status #{status}")
      deferred.resolve(data)
    )
    .error((data, status, headers) =>
      @$log.error("Failed to add link to User- status #{status}")
      deferred.reject(data);
    )
    deferred.promise

  deleteUser: (uuid) ->
    @$log.debug "deleteUser()"
    deferred = @$q.defer()

    @$http.delete("/user/#{uuid}")
    .success((data, status, headers) =>
      @$log.info("Successfully remove User - status #{status}")
      deferred.resolve(data)
    )
    .error((data, status, headers) =>
      @$log.error("Failed to remove User - status #{status}")
      deferred.reject(data);
    )
    deferred.promise

  updateUser: (user) ->
    @$log.debug "updateUser #{angular.toJson(user, true)}"
    deferred = @$q.defer()

    @$http.post('/user/update', user)
    .success((data, status, headers) =>
      @$log.info("Successfully update User - status #{status}")
      deferred.resolve(data)
    )
    .error((data, status, headers) =>
      @$log.error("Failed to update user - status #{status}")
      deferred.reject(data);
    )
    deferred.promise

servicesModule.service('UserService', UserService)