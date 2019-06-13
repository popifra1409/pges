/**
 * 
 */

$(document).ready(function() {
	$('.nBtn, .table .eBtn').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		var text = $(this).text();
		if (text == 'Edit') {

			if (text == "Acteur") {
				$('.myForm #act').prop('checked', true);
			}
			if (text == "Promoteur") {
				$('.myForm #pro').prop('checked', true);
			}
			if (text == "Responsable") {
				$('.myForm #res').prop('checked', true);
			}

			$.get(href, function(personne, status) {
				$('.myForm #nom').val(personne.nomPersonne);
				$('.myForm #pnom').val(personne.pnomPersonne);
				if (personne.sexe == "Masculin") {
					$('.myForm #masc').prop('checked', true);
					$('.myForm #femi').prop('checked', false);
				} else if (personne.sexe == "Féminin") {
					$('.myForm #femi').prop('checked', true);
					$('.myForm #masc').prop('checked', false);
				}
				$('.myForm #adresse').val(personne.adresse);
				$('.myForm #tel').val(personne.telPersonne);
				$('.myForm #fonction').val(personne.fonction);
			});
			$('.myForm #exampleModal').modal();
		} else {
			$('.myForm #act').prop('checked', true);
			$('.myForm #nom').val('');
			$('.myForm #pnom').val('');
			$('.myForm #masc').prop('checked', false);
			$('.myForm #femi').prop('checked', false);
			$('.myForm #adresse').val('');
			$('.myForm #tel').val('');
			$('.myForm #fonction').val('');
			$('.myForm #exampleModal').modal();
		}
	});
	$('.table .delBtn').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$('#myModal #delRef').attr('href', href);
		$('#myModal').modal();
	});
});