var aplikasiPelatihan = angular.module('aplikasiPelatihan', []);

aplikasiPelatihan.controller('HaloController', function ($scope) {
    $scope.daftarEmail = [
        'tes1@mail.com',
        'tesalan@mail.com',
        'tes2wan@mail.com'
    ];
    
    $scope.kembali = function(){
        window.history.back();
    };

    $scope.tambahEmail = function () {
        $scope.daftarEmail.push($scope.email);

    };

    $scope.hapusEmail = function (x) {
        //console.log($scope.daftarEmaildaftarEmail);
        var lokasiIndex = $scope.daftarEmail.indexOf(x);
        if (lokasiIndex > -1) {
            $scope.daftarEmail.splice(lokasiIndex, 1);
        }
        //console.log($scope.daftarEmail);
    };
});

aplikasiPelatihan.controller('MateriController', function ($http, $scope){
    $scope.daftarMateri={};
    
    $scope.simpanMateri=function(){
        $http.post('/api/materi', $scope.materi).then(sukses, gagal);
        
        function sukses(response){
            //alert('sukses');
            $scope.updateDataMateri();
                       
        };
        
        function gagal(response){
            alert('Error'+response);
            console.log(response);
        };
        
    };
    
    $scope.hapusMateri=function(x){
        $http.delete('/api/materi/'+x.id).then(sukses, gagal);
                
        function sukses(response){
            //alert('sukses');
            $scope.updateDataMateri();
                       
        };
        
        function gagal(response){
            alert('Error'+response);
            console.log(response);
        };
        
    };
    
    $scope.updateDataMateri=function(){
        $http.get('/api/materi').then(sukses, gagal);
      
        function sukses(response){
            //alert('sukses');
            $scope.daftarMateri = response.data;
            console.log($scope.daftarMateri);
            
            
        };
        
        function gagal(response){
            alert('Error'+response);
        };
    };
    $scope.updateDataMateri();
});

aplikasiPelatihan.controller('UsersController', function ($http, $scope){
    $scope.daftarUsers={};
    
    $scope.simpanMateri=function(){
        $http.post('/api/users', $scope.materi).then(sukses, gagal);
        
        function sukses(response){
            //alert('sukses');
            $scope.updateDataUsers();
                       
        };
        
        function gagal(response){
            alert('Error'+response);
            console.log(response);
        };
        
    };
    
    $scope.hapusUsers=function(x){
        $http.delete('/api/users/'+x.id).then(sukses, gagal);
                
        function sukses(response){
            //alert('sukses');
            $scope.updateDataUsers();
                       
        };
        
        function gagal(response){
            alert('Error'+response);
            console.log(response);
        };
        
    };
    
    $scope.updateDataUsers=function(){
        $http.get('/api/users').then(sukses, gagal);
      
        function sukses(response){
            //alert('sukses');
            $scope.daftarUsers = response.data;
            console.log($scope.daftarUsers);
            
            
        };
        
        function gagal(response){
            alert('Error'+response);
        };
    };
    $scope.updateDataUsers();
});





