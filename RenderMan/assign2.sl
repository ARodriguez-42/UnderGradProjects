/* BRDF returns constant for isotropic diffuse reflections.
** Note that the light attenuation occurs in the integration
** of the BRDF from all light sources.
** BRDF is the ratio of incident light to reflected light.
*/
vector H(vector V; vector L){
	return normalize( V + normalize(L) );
}

vector R(vector L; vector N; vector V){
	vector temp = normalize( 2*(N*L)*N*-L * normalize(-L) );
	return normalize( V + normalize(temp) );
}


color diffuseBRDF() {
  return Cs; // this is just the diffuse color constant
}


/*
** Note that the light attenuation occurs in the integration
** of the BRDF from all light sources...see illuminance loop.
** BRDF is the ratio of incident light to reflected light.
*/

float specularBRDF( vector H; normal Nf; float roughness ) 
{ 
  return pow( H . Nf, 1 / roughness );
}



color plasticBRDF(float spec; color specularcolor) 
{
  float diffusefract = 0.6;
  return specularcolor * (1 - diffusefract) * spec + diffusefract * diffuseBRDF();
}



surface assign(float model = 0; color specularcolor = 1; float roughness = 0.1;) {
   
   /* Initialization */
   normal Nf = faceforward (normalize(N),I);
   vector V = - normalize(I);
   color total_reflectance = 0;

   
   /* Integrate BRDF over Hemisphere */
   illuminance(P, Nf, PI/2) 
   {
      float incident_factor = normalize( L ) . Nf;
      
      	if(model == 1){
      	total_reflectance += Cl * incident_factor * diffuseBRDF();
      	}
      	else if(model == 2){     
      	vector H = H(V,L);	
      	total_reflectance += Cl * incident_factor * specularBRDF(H, Nf, roughness) * specularcolor;
      	}
      	else if(model == 3){ 
      	vector R = R(L, N, V);  	
      	total_reflectance += Cl * incident_factor * specularBRDF(R, Nf, roughness ) * specularcolor;
      	}
      	else if(model == 4){  
      	vector H = H(V,L);   
      	float spec = specularBRDF(H, Nf, roughness);
      	total_reflectance += Cl * incident_factor * plasticBRDF(spec, specularcolor);
      	}
      	else {
      	vector R = R(L, N, V); 
      	float spec = specularBRDF(R, Nf, roughness);
      	total_reflectance += Cl * incident_factor * plasticBRDF(spec, specularcolor);
      	}
      
     
   }
   
   Ci = total_reflectance;
}
