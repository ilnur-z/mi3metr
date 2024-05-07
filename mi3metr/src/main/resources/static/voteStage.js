	$(document).ready(function(){
	   $('a').on("click", function(){
	       $(this).prev().prop('checked', true);
	       $('#voteForm').submit();
	   });
	});
