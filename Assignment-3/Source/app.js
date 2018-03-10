// 'use strict';

// Declare app level module which depends on views, and components
angular.module('youtubeapp', [])

    .controller('searchController', function ($scope, $http, $sce) {

        $scope.getYoutubeLinks = function () {
            var searchKey = document.getElementById("KeywordSearch").value;
            $scope.YoutubeLink = new Array();
            if (searchKey != null && searchKey != "") {

                var handler = $http.get("https://www.googleapis.com/youtube/v3/search?part=snippet&q=" 
                    + searchKey + "&key=AIzaSyBg4hWRNAzxjBQbBbrba8pBav0WRTbk-WI");

                handler.then(function successCallback(data) {
                    for (var i = 0; i < 5; i++) {

                        var filter = data.data.items[i].id.videoId;
                        console.log(data.data.items[i].snippet.title);
                        $scope.YoutubeLink[i] = {
                            "title": data.data.items[i].snippet.title,
                            "vid": $sce.trustAsResourceUrl('https://www.youtube.com/embed/' + filter)
                        };
                    }


                });

            }
        }
    });