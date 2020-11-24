$('.minus-bttn').on('click', function(e) {
	e.preventDefault();
	var $this = $(this);
	var $input = $this.closest('div').find('input');
	var value = parseInt($input.val());

	if (value > 1) {
		value = value - 1;
	} else {
		value = 0;
	}
	

	$input.val(value);
	updateQuantity($input);
	

});

$('.plus-bttn').on('click', function(e) {
	e.preventDefault();
	var $this = $(this);
	var $input = $this.closest('div').find('input');
	var value = parseInt($input.val());

	if (value < 100) {
		value = value + 1;
	} else {
		value = 100;
	}

	$input.val(value);
	updateQuantity($input);
});

$('.like-btn').on('click', function() {
	$(this).toggleClass('is-active');
});



/* Set rates + misc */

var shippingRate = 15.00; 
var fadeTime = 300;


/* Assign actions */
$('.quantity input').change( function() {	
  updateQuantity(this);
});

$('.delete-btn').click( function() {
  removeItem(this);
});


/* Recalculate cart */
function recalculateCart()
{
  var subtotal = 0;
  
  /* Sum up row totals */
  $('.item').each(function () {
    subtotal += parseFloat($(this).children('.total-price').text());
  });
  
  /* Calculate totals */

  var shipping = (subtotal > 0 ? shippingRate : 0);
  var total = subtotal + shipping;
  
  /* Update totals display */
  $('.totals-value').fadeOut(fadeTime, function() {
    $('#cart-subtotal').html(subtotal.toFixed(2));
    $('#cart-shipping').html(shipping.toFixed(2));
    $('#cart-total').html(total.toFixed(2));
    if(total == 0){
      $('.orderbtn').fadeOut(fadeTime);
    }else{
      $('.orderbtn').fadeIn(fadeTime);
    }
    $('.totals-value').fadeIn(fadeTime);
  });
}


/* Update quantity */
function updateQuantity(quantityInput)
{
  /* Calculate line price */
  var productRow = $(quantityInput).parent().parent();
  var price = parseFloat(productRow.children('.description').children('.unit-price').text());
  var quantity = $(quantityInput).val();
  var linePrice = price * quantity;
  

  /* Update line price display and recalc cart totals */
  productRow.children('.total-price').each(function () {
    $(this).fadeOut(fadeTime, function() {
      $(this).text(linePrice.toFixed(2));
      recalculateCart();
      $(this).fadeIn(fadeTime);
    });
  });  
}

/* On page load */
window.onload = function exampleFunction() { 
            recalculateCart();
        } 

/* Remove item from cart */
function removeItem(removeButton)
{
  /* Remove row from DOM and recalc cart total */
  var productRow = $(removeButton).parent().parent();
	var detalle = {
		id: productRow.children('.id').text()
	}

	toastr["success"]("Elemento eliminado!")
	

 	productRow.slideUp(fadeTime, function() {
	$.ajax({
		type: "GET",
        url: "/cart/delete",
        data: detalle,
	});
    productRow.remove();
    recalculateCart();
  });
}

toastr.options = {
		"closeButton": false,
		"debug": false,
		"newestOnTop": true,
		"progressBar": false,
		"positionClass": "toast-top-right",
		"preventDuplicates": false,
		"onclick": null,
		"showDuration": "300",
		"hideDuration": "1000",
		"timeOut": "2500",
		"extendedTimeOut": "1000",
		"showEasing": "swing",
		"hideEasing": "linear",
		"showMethod": "fadeIn",
		"hideMethod": "fadeOut"
	}
	
