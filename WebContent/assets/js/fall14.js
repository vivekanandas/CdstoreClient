/**
 * created by : Indhrapriya Shanmugam
 * 
 */
$(function(){
	cd.retrive('pop');
	cart.display();
});
var cd = {};
cd.retrive = function(categoryName){
	//alert('cd.retrive');
	$('#productslider').html("");
	$.ajax({
		async :	false, 
		url   : '//localhost:7080/CDOrderProcessing/OPService/CdService/getListByCategoryName/'+categoryName,
		type  : 'get',
		data  : {},
		dataType : 'json'
}).done(function(data){
	if(data){
		$.each(data, function() {
			var title, artist, year, price;
			//alert(this.title);
			var content = '<div class="item"><div class="product"><div class="image"><a href="product-details.html"><img src="images/product/'+this.title.replace(/\s/g, '')+'.jpg" alt="img" class="img-responsive"></a></div><div class="description"><h4><a href="product-details.html">'+this.title+'</a></h4><p>'+this.artist+'</p><span class="size">'+this.year+'</span> </div><div class="price"><span>'+this.price+'</span> </div><div class="action-control"><a class="btn btn-primary addToCart" data-id="'+this.id+'"> <span class="add2cart"><i class="glyphicon glyphicon-shopping-cart"></i> Add to cart </span> </a> </div></div></div>';
			console.log(content);
			$('#productslider').append(content);
	});
		$(".addToCart").on('click',function(){
	  		cart.add($(this).data('id'));	
	  	});
		cart.display();
		
	}
	else{
		alert('unknown error');
	}
});
}
var cart = {};

cart.add = function(id){
	$.ajax({
		async :	false, 
		url   : 'ShopCart?method=add&id='+id,
		type  : 'get',
		data  : {},
		dataType : 'json'
		}).done(function(data){
		if(data.result){
			bootbox.alert('Item added in your cart.');
			cart.display();
			
			
		}
		else{
			bootbox.alert('Item Out of Stock');
			cart.display();
		}
	});
}

cart.display = function(){
//	alert('display');
	$.ajax({
		async :	false, 
		url   : 'ShopCart?method=get',
		type  : 'get',
		data  : {},
		dataType : 'json'
		}).done(function(data){
			//console.log("data = "+data);
			if(data){
				$('.cartBody').html("");
				$.each(data.cds,function(){
					var content = '<tr class="miniCartProduct"><td style="width:20%" class="miniCartProductThumb"><div> <a href="product-details.html" class="productDetail" data-id=""> <img src="images/product/'+this.title.replace(/\s/g, '')+'.jpg" alt="img"> </a> </div></td><td  style="width:40%"><div class="miniCartDescription"><h4> <a href="'+this.title.replace(/\s/g, '')+'.jsp">'+this.title+'</a> </h4><span class="size"></span><div class="price"> <span> $'+ this.price +' </span> </div></div></td><td   style="width:10%" class="miniCartQuantity"><a > X '+this.quantity+' </a></td><td  style="width:15%" class="miniCartSubtotal"><span> $'+this.subTotal+' </span></td><td  style="width:5%" class="deleteCartItem" data-id="'+this.id+'"><a > X </a></td></tr>';
					$('.cartBody').append(content);
				});
				$('.deleteCartItem').on('click',function(){
					cart.deleteItem($(this).data('id'));
				});
				$('.subtotal').html('Total : $'+data.total.toFixed(2));
				$('.totalTop').html('($'+data.total.toFixed(2)+')');
				
			}
			else{
				//bootbox.alert('Item Out of Stock');
			}
	});
	
}
//Delete Cart Item
cart.deleteItem = function(id){
	bootbox.confirm("Are you sure you want to remove this item ?", function(result) {
		if(result){
			$.ajax({
				async :	true, 
				url   : 'ShopCart?method=delete&id='+id,
				type  : 'get',
				data  : {},
				dataType : 'json'
				}).done(function(data){
				if(data.result){
					bootbox.alert('Item successfully removed from your cart.');
					cart.display();
				}
				else{
					bootbox.alert('Error removing');
					cart.display();
				}
			});
		}
	}); 
	
	
}