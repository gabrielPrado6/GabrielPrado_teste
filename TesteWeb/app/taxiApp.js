var taxiApp = angular.module('taxiApp', ["ngRoute"]);
taxiApp.config( function ($routeProvider) {

    $routeProvider
        .when('/', {templateUrl: 'app/view/home.html', controller: 'HomeCtrl'})
        .when('/client', {templateUrl: 'app/view/client.html', controller: 'ClientCtrl'})
        .when('/driver', {templateUrl: 'app/view/driver.html', controller: 'DriverCtrl'})
        .when('/login', {templateUrl: 'app/view/login.html', controller: 'LoginCtrl'})
        .when('/singin', {templateUrl: 'app/view/singin.html', controller: 'SinginCtrl'})
        .otherwise ({ redirectTo: '/' });
});

taxiApp.value("User", {
    user : '',
    driver : '',
    customer  : '',
    email  : '',
    name  : '',
    cpf  : '',
    nasc  : '',
    gender : ''
});

taxiApp.controller( 'HomeCtrl', function ($scope)
{
});
taxiApp.controller( 'ClientCtrl', function ($scope, User)
{

});
taxiApp.controller( 'DriverCtrl', function ($scope)
{
});
taxiApp.controller( 'LoginCtrl', function ($scope, $http, $window, User)
{

    $scope.submit = function () {
        $http({
            method: 'POST',
            url: 'app/serverconn/login.php',
            data: {email : $scope.logEmail, password : $scope.logPassword},
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        })
            .then(function (response) {
                $scope.logMessage = response.data.records[0].error;

                if(response.data.records[0].user != 'undefined'){
                    User.user = response.data.records[0].user;
                    User.driver =  response.data.records[0].driver;
                    User.customer =  response.data.records[0].customer;
                    User.email =  response.data.records[0].email;
                    User.name =  response.data.records[0].name;
                    User.cpf =  response.data.records[0].cpf;
                    User.nasc =  response.data.records[0].nasc;
                    User.gender =  response.data.records[0].gender;
                    $window.location.href = '#!/client';
                }else {
                }
            });



    }
});
taxiApp.controller( 'SinginCtrl', function ($scope, $http)
{
    $http.get('app/serverconn/carmodel.php')
        .then(function (response) {$scope.cars = response.data.records;});

    $scope.submit = function () {
        $http({
            method: 'POST',
            url: 'app/serverconn/login.php',
            data: {
                name : $scope.singName,
                email : $scope.singEmail,
                password : $scope.singPass,
                nasc : $scope.singNasc,
                cpf : $scope.singCPF,
                gender : $scope.singGender,
                isClient : $scope.singClient,
                driver : $scope.singDriver,
                model : $scope.modelCar,
                active : $scope.singDriverActive
            },
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        })
            .then(function (response) {

});


