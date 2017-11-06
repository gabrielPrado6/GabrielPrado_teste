taxiApp.controller( 'SinginCtrl', function ($rootScope, $location) {
    $rootScope.activetab = $location.path();
});