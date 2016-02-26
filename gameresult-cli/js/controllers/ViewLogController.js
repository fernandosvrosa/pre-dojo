angular.module('result').controller('ViewLogController', function($scope, $http, $routeParams){
    
    $scope.gameResult = '';
    $scope.id = $routeParams.GameId;
    
    $http.get('http://localhost:8080/v1/analysis/'+$routeParams.GameId).then(function(retorno){
            console.log(retorno.data);
            $scope.gameResult = retorno.data.gameResult;
        },
        function(erro){
            console.log(erro);
        });
   
  });