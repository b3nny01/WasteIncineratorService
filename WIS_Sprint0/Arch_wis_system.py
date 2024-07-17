### conda install diagrams
from diagrams import Cluster, Diagram, Edge
from diagrams.custom import Custom
import os
os.environ['PATH'] += os.pathsep + 'C:/Program Files/Graphviz/bin/'

graphattr = {     #https://www.graphviz.org/doc/info/attrs.html
    'fontsize': '22',
}

nodeattr = {   
    'fontsize': '22',
    'bgcolor': 'lightyellow'
}

eventedgeattr = {
    'color': 'red',
    'style': 'dotted'
}
evattr = {
    'color': 'darkgreen',
    'style': 'dotted'
}
with Diagram('wis_systemArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctx_wis', graph_attr=nodeattr):
          incinerator=Custom('incinerator','./qakicons/symActorSmall.png')
          waste_storage=Custom('waste_storage','./qakicons/symActorSmall.png')
          ash_storage=Custom('ash_storage','./qakicons/symActorSmall.png')
          op_robot=Custom('op_robot','./qakicons/symActorSmall.png')
          waste_incinerator_service=Custom('waste_incinerator_service','./qakicons/symActorSmall.png')
     waste_incinerator_service >> Edge(color='magenta', style='solid', decorate='true', label='<incinerator_state_request &nbsp; >',  fontcolor='magenta') >> incinerator
     waste_incinerator_service >> Edge(color='magenta', style='solid', decorate='true', label='<waste_storage_state_request &nbsp; >',  fontcolor='magenta') >> waste_storage
     waste_incinerator_service >> Edge(color='magenta', style='solid', decorate='true', label='<ash_storage_state_request &nbsp; >',  fontcolor='magenta') >> ash_storage
     op_robot >> Edge(color='magenta', style='solid', decorate='true', label='<rp_request &nbsp; >',  fontcolor='magenta') >> waste_storage
diag
