angular.module('result').controller('HomeController', function($scope, $http, $window, $rootScope){
    $scope.file ='';
    $scope.results = [];
    $scope.onChange = function (e, fileList) {
      alert('arquivo convertido para base64 e proto para ser enviado para o servidor, click em "Enviar"!');
    };

    $scope.onLoad = function (e, reader, file, fileList, fileOjects, fileObj) {
      alert('arquivo convertido para base64 e proto para ser enviado para o servidor, click em "Enviar"!');
    };

   

    $scope.sendToServer = function(file){
      console.log(file);
      $http.post('http://localhost:8080/v1/analysis', file).then(function(retorno){
         $scope.file ='';
         results();
      },
      function(erro){
          console.log(erro);
          $scope.file = '';
      });
    }
    
    
    
    function results(){
        $http.get('http://localhost:8080/v1/analysis').then(function(retorno){
            console.log(retorno);
            $scope.results = retorno.data.gameResultIds;
        },
        function(erro){
            console.log(erro);
        });
    }
    
    
    results();
    

  });
