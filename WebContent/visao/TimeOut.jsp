<%! int tempo=0; %>
<%

	long segundos = (session.getMaxInactiveInterval()-10);
	long minutos =  0;
	long horas = 0;

	long o1_Segundo = 1000;
	long o1_Minuto = (60 * o1_Segundo);
	long o1_Hora = (60 * o1_Minuto);

	long tempo = ((o1_Hora * horas) + (o1_Minuto * minutos) + (o1_Segundo * segundos));
%>


<script language="JavaScript">

	var timer, tempo, segundos;
	
	timer =  <%=tempo%>;
	
	timer = timer / 1000;
	function clock()
	{
		segundos = timer % 60;
		if (segundos < 10)
		{
			segundos = "0" + segundos;
		}
		tempo = (timer - timer % 60) / 60 + ":" + segundos;
		Clock.innerHTML = tempo;
		if (timer <= 0)
		{
			top.location.replace('/publicadoranuncio/visao/PoPup.html');
		}
		else
		{
			Delay = setTimeout("clock()", 1000);
		}
		timer = timer - 1;
	}
</script>
<div id=Clock align="right" style="font-family: Arial; font-size: 12; color:darkblue"></div>
<script>clock();</script>


