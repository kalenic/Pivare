var wafepa = angular.module("firstModule", ['ngRoute']);




wafepa.controller("pivaCtrl", function($scope, $http, $location){
	
	var baseUrl = "/api/piva";
	var baseUrlPivare = "/api/pivare"
	var baseUrlVrstePiva = "/api/vrste_piva"
		
	$scope.promeni = true;
	$scope.$watch('promeni', function(){
        $scope.nazivForme = $scope.promeni ? 'Dodavanje' : 'Pretraga';
    })
    $scope.izmena = false;
		
	$scope.gotPiva = false;
	
	$scope.piva = [];
	$scope.pivare = [];
	$scope.vrstePiva = [];
	
	$scope.novoPivo = {};
	$scope.novoPivo.naziv = "";
	$scope.novoPivo.alkohol = "";
	$scope.novoPivo.ibu = "";
	$scope.novoPivo.kolicina = "";
	$scope.novoPivo.pivaraId = 0;
	$scope.novoPivo.vrstaPivaId = 0;
	
	$scope.trazenoPivo = {};
	$scope.trazenoPivo.naziv="";
	$scope.trazenoPivo.minIbu="";
	$scope.trazenoPivo.maxIbu="";
	$scope.trazenoPivo.nazivPivare="";
	
	$scope.pivoZaIzmenu = {};
//	$scope.knjigaRevert={};
	
	$scope.pageNum=0;
	$scope.totalPages = 1;
	
//	var watch = function() {
//		if($scope.pageNum >= $scope.totalPages) {
//			$scope.pageNum = $scope.totalPages - 1;
//			getKnjige();
//		}
//	}
	
//	$scope.myCustomFilter = function(element) {
//	    if ($scope.FilterByType) {
//	        return element === parseInt($scope.FilterByType);
//	    }
//	    return true;
//	}
	
	var getPiva = function(){
		
		var config = {params: {}};
		
		if($scope.trazenoPivo.naziv !=""){
			config.params.naziv =  $scope.trazenoPivo.naziv;
		}
		if($scope.trazenoPivo.minIbu !=""){
			config.params.minIbu =  $scope.trazenoPivo.minIbu;
		}
		if($scope.trazenoPivo.maxIbu !=""){
			config.params.maxIbu =  $scope.trazenoPivo.maxIbu;
		}
		if($scope.trazenoPivo.nazivPivare !=""){
			config.params.nazivPivare =  $scope.trazenoPivo.nazivPivare;
		}
		
		config.params.pageNum=$scope.pageNum;
		
		$http.get(baseUrl, config).then(
			function success(x){
//				console.log(x);
				$scope.totalPages = x.headers("totalPages");
//				watch();
				$scope.piva = x.data;
				$scope.gotPiva = true;
			},
			function error(data){
				alert("Nije uspelo dobavljanje piva!");
			}
		);
	}
	
	
	var getPivare = function(){
		
		$http.get(baseUrlPivare).then(
			function success(x){
				$scope.pivare = x.data;
//				console.log($scope.pivare);
			},
			function error(x){
				alert("Neuspesno dobavljanje pivara.");
			}
		);		
	}
	
	var getVrstePiva = function(){
		
		$http.get(baseUrlVrstePiva).then(
			function success(x){
				$scope.vrstePiva = x.data;
			},
			function error(x){
				alert("Neuspesno dobavljanje vrsta piva.");
			}
		);		
	}
	
	getPiva();
	getPivare();
	getVrstePiva();
//	console.log($scope.pivare);
	
	
	
	var getPivo = function(id){
			
			var promise = $http.get(baseUrl + "/" + id);

			promise.then(
				function success(data){
//					console.log(data);
					$scope.pivoZaIzmenu = data.data;
					$scope.setPivo();
					
				},
				function failure(data){
					alert("Neuspesno dobavljanje piva.");
				}
			);
			
		}
	
	$scope.revert = function() {
//		console.log($scope.knjigaZaIzmenu);
//		console.log($scope.novaKnjiga);
		$scope.setPivo();
	}
	
	
	$scope.setPivo = function(){
		 
		var naziv = $scope.pivoZaIzmenu.naziv;
		var alkohol = $scope.pivoZaIzmenu.alkohol;
		var ibu = $scope.pivoZaIzmenu.ibu;
		var kolicina = $scope.pivoZaIzmenu.kolicina;
		var pivaraId = $scope.pivoZaIzmenu.pivaraId;
		var vrstaPivaId = $scope.pivoZaIzmenu.vrstaPivaId;
		
		$scope.novoPivo.naziv = naziv;
		$scope.novoPivo.alkohol = alkohol;
		$scope.novoPivo.ibu = ibu;
		$scope.novoPivo.kolicina = kolicina;
		$scope.novoPivo.pivaraId = pivaraId;
		$scope.novoPivo.vrstaPivaId = vrstaPivaId; 
		
		
	}
	
	var editPivo = function() {

		if($scope.novoPivo.naziv == "" || $scope.novoPivo.alkohol == "" || 
				$scope.novoPivo.ibu == "" || $scope.novoPivo.kolicina == "" || 
				$scope.novoPivo.pivaraId  == "" || $scope.novoPivo.vrstaPivaId == "") {
			alert("Moraju sva polja biti popunjena");
		} 
		else {
			
			$scope.pivoZaIzmenu.naziv = $scope.novoPivo.naziv;
			$scope.pivoZaIzmenu.alkohol = $scope.novoPivo.alkohol;
			$scope.pivoZaIzmenu.ibu = $scope.novoPivo.ibu;
			$scope.pivoZaIzmenu.kolicina = $scope.novoPivo.kolicina;
			$scope.pivoZaIzmenu.pivaraId = $scope.novoPivo.pivaraId;
			$scope.pivoZaIzmenu.vrstaPivaId = $scope.novoPivo.vrstaPivaId;
			
			$http.put(baseUrl + "/" + $scope.pivoZaIzmenu.id, $scope.pivoZaIzmenu).then(
					function success(data){
						getPiva();
						$scope.izmena = false;
						$scope.novoPivo = {};
						$scope.resetFields(); 
					},
					function failure(data){
						alert("Neuspela izmena piva.");
						$scope.izmena = false;
						$scope.resetFields(); 
					}
			);
		}
	}
	
	$scope.savePivo = function(){

		if(!$scope.izmena) {
			$scope.add();
		} else if($scope.izmena){
			editPivo();
		}
	}
	

	
	$scope.resetFields = function() {
		$scope.novoPivo.naziv = "";
		$scope.novoPivo.alkohol = "";
		$scope.novoPivo.ibu = "";
		$scope.novoPivo.kolicina = "";
		$scope.novoPivo.pivaraId = "";
		$scope.novoPivo.vrstaPivaId = 0;
	}
	
	$scope.add = function(){
		console.log($scope.novoPivo);
		$http.post(baseUrl, $scope.novoPivo).then(
			function uspeh(data){
				getPiva();
				$scope.resetFields();
			},
			function neuspeh(data){
				alert("Nije uspelo dodavanje novog piva.");
				console.log(data);
				$scope.resetFields(); 
			} 		
		);
	}
	
	 $scope.izmeni = function(id){
	        $location.path('/piva/edit/' + id);
	    }
	 
	
	 $scope.izmeniIsta = function(id){
		 $scope.izmena = true;
		 getPivo(id);
	 }
	 
	 $scope.odustani = function() {
			$scope.resetFields();
			$scope.novoPivo = {};
			$scope.izmena = false;
		}
	 
	 $scope.obrisi = function(id){
	        $http.delete(baseUrl + "/" + id).then(
	            function success(data){
	            	getPiva();
	            },
	            function error(data){
	                alert("Neuspesno brisanje!");
	            }
	        );
	    }
	 
	 $scope.kupi = function(id){
	    	$http.post(baseUrl + "/" + id + "/kupovina").then(
	    		function success(data){
	    			alert("Pivo je uspesno kupljeno.");
	    			getPiva();
	    		},
	    		function error(data){
	    			alert("Nije uspela kupovina piva.")
	    		}
	    	)
	    }
	
	$scope.filter = function(){
		getPiva();
	}
	
	$scope.changePage = function(dir){
		$scope.pageNum = $scope.pageNum + dir;
		getPiva();
	}
	
//	$scope.veciOdSto = function(){
//		$scope.trazeniStand.zakupac="";
//		$scope.trazeniStand.maxP="";
//		$scope.trazeniStand.minP=100;
//		getStandovi();
//	}
	
});


wafepa.controller("editPivoCtrl", function($scope, $http, $routeParams, $location){
	
	//Kako je prilikom konfiguracije ruta receno da je deo rute ka partialu u kojem se kreira
	//ovaj kontroler parametar, taj parametar preuzimamo koriscenjem $routeParams servisa.
	//Par linija ispod je zakomentarisano logovanje ovog servisa u konzolu, otkomentarisati
	//radi demonstracije.
	var id = $routeParams.pid;
	var baseUrl = "/api/piva";
	var baseUrlPivare = "/api/pivare"
	var baseUrlVrstePiva = "/api/vrste_piva"
	
	$scope.pivo = {};
	
	$scope.pivare = [];
	$scope.vrstePiva = [];
	
	console.log($routeParams);
	
	//Dobavljamo aktivnost za koju je zahtevana izmena..
	var getPivo = function(){
		
		//dodajem i / znak, jer bi inace bilo /api/activitiesID, a to bekend ne prepoznaje.
		//Sa $http gadjam rutu za dobavljanje jednog, stoga se u data nece naci niz, vec objekat.
		var promise = $http.get(baseUrl + "/" + id);
		promise.then(
			function success(obj){
				$scope.pivo = obj.data;
			},
			function error(obj){
				alert("Neuspesno dobavljanje piva.");
			}
		);
	}
	
	
	var getPivare = function(){
			
			$http.get(baseUrlPivare).then(
				function success(x){
					$scope.pivare = x.data;
				},
				function error(x){
					alert("Neuspesno dobavljanje pivara.");
				}
			);		
	}
	
	var getVrstePiva = function(){
		
		$http.get(baseUrlVrstePiva).then(
			function success(x){
				$scope.vrstePiva = x.data;
			},
			function error(x){
				alert("Neuspesno dobavljanje vrsta piva.");
			}
		);		
	}
	
	
	getPivo();
	getPivare();
	getVrstePiva();
	
	$scope.edit = function(){
		
		//Put izgleda onako kako bekend zahteva od njega - da bude id menjane aktivnosti
		//i da se u podacima javi sama aktivnost, koja takodje poseduje id.
		$http.put(baseUrl + "/" + id, $scope.pivo).then(
			function success(data){
				//I na kraju se pomocu $location servisa premestamo na partial u kome su
				//prikazane sve aktivnosti.
				$location.path("/piva");
			},
			function error(data){
				alert("Neuspela izmena piva.");
			}
		);		
	}
});









wafepa.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/partial/home.html'
		})

		.when('/piva', {
			templateUrl : '/app/html/partial/piva.html'
		})
		.when('/piva/edit/:pid', {//Ovakvo oznacavanje sa dvotackom je novina! Ovako oznacen deo rute ce se naci u $routeParams servisu.
			templateUrl : '/app/html/partial/edit_pivo.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);

