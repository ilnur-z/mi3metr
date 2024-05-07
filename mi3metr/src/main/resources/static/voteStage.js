	$(document).ready(function(){
	   $('.candidateLink').on("click", function(){
	       $(this).prev().prop('checked', true);
	       $('#voteForm').submit();
	   });
	   $('.candidateImage').on("click", function(){
	       $(this).next().prop('checked', true);
	       $('#voteForm').submit();
	   });
	});
