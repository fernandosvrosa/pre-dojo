angular.module('result').controller('HomeController', function($scope, $http, $window, $rootScope){
    $scope.file ='';
    $scope.onChange = function (e, fileList) {
      alert('this is on-change handler!');
    };

    $scope.onLoad = function (e, reader, file, fileList, fileOjects, fileObj) {
      alert('this is handler for file reader onload event!');
    };

    File = {
      "fileSize": 0, /* bytes */
      "fileType": "image/jpeg",
      "fileName": "",
      "base64":   ""
    }

    $scope.sendToServer = function(file){
      console.log(file);
      $http.post('http://localhost:8080/v1/analysis', file).then(function(retorno){
        console.log(retorno);
          $scope.retorno = retorno.data.id;
      },
      function(erro){
          console.log(erro);
      });
    }

  });
