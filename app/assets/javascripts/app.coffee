dependencies = [
  'ngRoute',
  'ui.bootstrap',
  'myApp.filters',
  'myApp.services',
  'myApp.controllers',
  'myApp.directives',
  'myApp.common',
  'myApp.routeConfig'
]

app = angular.module('myApp', dependencies)

angular.module('myApp.routeConfig', ['ngRoute'])
.config ($routeProvider) ->
  $routeProvider
  .when('/', {
      templateUrl: '/assets/partials/user/view.html'
    })
  .when('/users/create', {
      templateUrl: '/assets/partials/user/create.html'
    })
  .otherwise({redirectTo: '/'})
  .when('/links/create', {
      templateUrl: '/assets/partials/link/create.html'
    })
  .when('/links', {
      templateUrl: '/assets/partials/link/view.html'
    })
  .otherwise({redirectTo: '/'})

@commonModule = angular.module('myApp.common', [])
@controllersModule = angular.module('myApp.controllers', [])
@servicesModule = angular.module('myApp.services', [])
@modelsModule = angular.module('myApp.models', [])
@directivesModule = angular.module('myApp.directives', [])
@filtersModule = angular.module('myApp.filters', [])