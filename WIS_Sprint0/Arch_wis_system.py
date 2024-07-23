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
          wis_state_observer=Custom('wis_state_observer','./qakicons/symActorSmall.png')
          wis_incinerator_scheduler=Custom('wis_incinerator_scheduler','./qakicons/symActorSmall.png')
     op_robot >> Edge(color='magenta', style='solid', decorate='true', label='<burn_req<font color="darkgreen"> burn_repl</font> &nbsp; >',  fontcolor='magenta') >> incinerator
     wis_incinerator_scheduler >> Edge(color='magenta', style='solid', decorate='true', label='<activation_req<font color="darkgreen"> activation_repl</font> &nbsp; >',  fontcolor='magenta') >> incinerator
     op_robot >> Edge(color='magenta', style='solid', decorate='true', label='<conditions_verified_req<font color="darkgreen"> conditions_verified_repl</font> &nbsp; >',  fontcolor='magenta') >> wis_state_observer
     op_robot >> Edge(color='magenta', style='solid', decorate='true', label='<ash_req<font color="darkgreen"> ash_repl</font> &nbsp; >',  fontcolor='magenta') >> ash_storage
     op_robot >> Edge(color='magenta', style='solid', decorate='true', label='<rp_req<font color="darkgreen"> rp_repl</font> &nbsp; >',  fontcolor='magenta') >> waste_storage
     ash_storage >> Edge(color='blue', style='solid',  decorate='true', label='<actor_state &nbsp; >',  fontcolor='blue') >> wis_state_observer
     incinerator >> Edge(color='blue', style='solid',  decorate='true', label='<actor_state &nbsp; >',  fontcolor='blue') >> wis_state_observer
     waste_storage >> Edge(color='blue', style='solid',  decorate='true', label='<actor_state &nbsp; >',  fontcolor='blue') >> wis_state_observer
diag
