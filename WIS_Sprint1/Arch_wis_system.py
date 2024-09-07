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
          scale=Custom('scale','./qakicons/symActorSmall.png')
          monitoring_device=Custom('monitoring_device','./qakicons/symActorSmall.png')
          op_robot=Custom('op_robot','./qakicons/symActorSmall.png')
          wis=Custom('wis','./qakicons/symActorSmall.png')
     with Cluster('ctx_basic_robot', graph_attr=nodeattr):
          basicrobot=Custom('basicrobot(ext)','./qakicons/externalQActor.png')
     sys >> Edge( label='load_rp', **evattr, decorate='true', fontcolor='darkgreen') >> scale
     sys >> Edge( label='unload_ash', **evattr, decorate='true', fontcolor='darkgreen') >> monitoring_device
     op_robot >> Edge( label='load_rp', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     op_robot >> Edge( label='unload_ash', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     op_robot >> Edge(color='magenta', style='solid', decorate='true', label='<system_state_req<font color="darkgreen"> system_state_repl</font> &nbsp; >',  fontcolor='magenta') >> wis
     op_robot >> Edge(color='magenta', style='solid', decorate='true', label='<burn_req<font color="darkgreen"> burn_repl</font> &nbsp; >',  fontcolor='magenta') >> incinerator
     op_robot >> Edge(color='magenta', style='solid', decorate='true', label='<engage<font color="darkgreen"> engagedone engagerefused</font> &nbsp; moverobot<font color="darkgreen"> moverobotdone moverobotfailed</font> &nbsp; >',  fontcolor='magenta') >> basicrobot
     incinerator >> Edge(color='blue', style='solid',  decorate='true', label='<actor_state &nbsp; >',  fontcolor='blue') >> wis
     monitoring_device >> Edge(color='blue', style='solid',  decorate='true', label='<actor_state &nbsp; >',  fontcolor='blue') >> wis
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<incinerator_activation &nbsp; >',  fontcolor='blue') >> incinerator
     scale >> Edge(color='blue', style='solid',  decorate='true', label='<actor_state &nbsp; >',  fontcolor='blue') >> wis
diag
