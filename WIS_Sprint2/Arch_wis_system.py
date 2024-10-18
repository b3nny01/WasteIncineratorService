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
          op_robot=Custom('op_robot','./qakicons/symActorSmall.png')
          wis=Custom('wis','./qakicons/symActorSmall.png')
          test_observer=Custom('test_observer','./qakicons/symActorSmall.png')
          sonar=Custom('sonar','./qakicons/symActorSmall.png')
          led=Custom('led','./qakicons/symActorSmall.png')
     with Cluster('ctx_basic_robot', graph_attr=nodeattr):
          basicrobot=Custom('basicrobot(ext)','./qakicons/externalQActor.png')
     op_robot >> Edge(color='magenta', style='solid', decorate='true', label='<system_state_req<font color="darkgreen"> system_state_repl</font> &nbsp; >',  fontcolor='magenta') >> wis
     op_robot >> Edge(color='magenta', style='solid', decorate='true', label='<burn_req<font color="darkgreen"> burn_repl</font> &nbsp; >',  fontcolor='magenta') >> incinerator
     op_robot >> Edge(color='magenta', style='solid', decorate='true', label='<engage<font color="darkgreen"> engagedone engagerefused</font> &nbsp; moverobot<font color="darkgreen"> moverobotdone moverobotfailed</font> &nbsp; >',  fontcolor='magenta') >> basicrobot
     test_observer >> Edge(color='magenta', style='solid', decorate='true', label='<system_state_req<font color="darkgreen"> system_state_repl</font> &nbsp; >',  fontcolor='magenta') >> wis
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<system_state &nbsp; >',  fontcolor='blue') >> led
     sonar >> Edge(color='blue', style='solid',  decorate='true', label='<actor_state &nbsp; >',  fontcolor='blue') >> wis
     op_robot >> Edge(color='blue', style='solid',  decorate='true', label='<actor_state &nbsp; >',  fontcolor='blue') >> wis
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<incinerator_activation &nbsp; >',  fontcolor='blue') >> incinerator
     scale >> Edge(color='blue', style='solid',  decorate='true', label='<actor_state &nbsp; >',  fontcolor='blue') >> wis
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<system_state &nbsp; >',  fontcolor='blue') >> test_observer
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<system_state &nbsp; >',  fontcolor='blue') >> sonar
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<system_state &nbsp; >',  fontcolor='blue') >> scale
     incinerator >> Edge(color='blue', style='solid',  decorate='true', label='<actor_state &nbsp; >',  fontcolor='blue') >> wis
diag
