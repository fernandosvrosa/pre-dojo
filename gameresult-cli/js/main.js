angular.module('result', ['ngRoute', 'ngResource', 'naif.base64'])
.config(function($routeProvider, $httpProvider){

  $httpProvider.defaults.useXDomain = true;
  delete $httpProvider.defaults.headers.common['X-Requested-With'];
  $httpProvider.defaults.headers.common["Accept"] = "application/json";
  $httpProvider.defaults.headers.common["Content-Type"] = "application/json";

  $routeProvider.when('/',{
    templateUrl: 'partials/home.html',
    controller: 'HomeController'
  });

  $routeProvider.when('/:GameId',{
    templateUrl: 'partials/viewLog.html',
    controller: 'ViewLogController'
  });

  $routeProvider.otherwise({redirectTo: '/'});

});
