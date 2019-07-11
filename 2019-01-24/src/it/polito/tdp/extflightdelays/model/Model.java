package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {

	//inserire tipo di dao

		private ExtFlightDelaysDAO dao;

		

		//scelta valore mappa

		// private Map<Integer,E> idMap;

		

		//scelta tipo valori lista

		private List<String> vertex;

		

		//scelta tra uno dei due edges

		private List<Adiacenza> edges;

		

		//scelta tipo vertici e tipo archi

		private Graph<String, DefaultWeightedEdge> graph;
		
		private String source;
		private String target;

		

		public Model() {

			

			//inserire tipo dao

			dao  = new ExtFlightDelaysDAO();

			//inserire tipo values

			//idMap = new HashMap<Integer,E>();

		}

		

		public List<String> creaGrafo() {

			

			//scelta tipo vertici e archi

			graph = new DefaultDirectedWeightedGraph<String,DefaultWeightedEdge>(DefaultWeightedEdge.class);

			

			//scelta tipo valori lista

			vertex = new ArrayList<String>(dao.loadAllStates());

			Graphs.addAllVertices(graph,vertex);

			

			edges = new ArrayList<Adiacenza>(dao.loadAllEdges());

			

			for(Adiacenza a : edges) {

				

				//CASO BASE POTRESTI DOVER AGGIUNGERE CONTROLLI

				/*District*/ source = a.getId1();

				/*District*/ target = a.getId2();

				double peso = a.getPeso();

				Graphs.addEdge(graph,source,target,peso);

				System.out.println("AGGIUNTO ARCO TRA: "+source.toString()+" e "+target.toString());

				

			}

			

			System.out.println("#vertici: "+ graph.vertexSet().size());

			System.out.println("#archi: "+ graph.edgeSet().size());
			
			Collections.sort(vertex);
			
			return vertex;

			

		}



		public String visualizzaVeivoli(String s) {
			
			String ris= "Lista degli stati collegati direttamente a " + s + ": \n";
			List<String> successori= Graphs.successorListOf(this.graph, s);
			List<SuccessoriPesi> sucpesi= new ArrayList<>();
			
			for (String suc: successori)
			{
				
				DefaultWeightedEdge e= this.graph.getEdge(s, suc);
				int peso= (int) this.graph.getEdgeWeight(e);
				
				sucpesi.add(new SuccessoriPesi(suc,peso));
				
			}
			
			Collections.sort(sucpesi);
			
			for(SuccessoriPesi sp: sucpesi) {
				ris+= "- " + sp.getSuccessore() + " " + sp.getPeso() + "\n";
			}
			
			return ris;
			
			
		}

}
