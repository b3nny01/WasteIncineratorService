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
          incinerator=Custom('incinerator','./qakicons/symActorWithobjSmall.png')
          scale=Custom('scale','./qakicons/symActorWithobjSmall.png')
          op_robot=Custom('op_robot','./qakicons/symActorWithobjSmall.png')
          wis=Custom('wis','./qakicons/symActorWithobjSmall.png')
          sonar=Custom('sonar','./qakicons/symActorWithobjSmall.png')
          led=Custom('led','./qakicons/symActorWithobjSmall.png')
          msg_receiver=Custom('msg_receiver','./qakicons/symActorWithobjSmall.png')
          test_observer=Custom('test_observer','./qakicons/symActorWithobjSmall.png')
     wisfacade=Custom('wisfacade','./qakicons/server.png')
     with Cluster('ctx_basic_robot', graph_attr=nodeattr):
          basicrobot=Custom('basicrobot(ext)','./qakicons/externalQActor.png')
     wisfacade=Custom('wisfacade','./qakicons/server.png')
     sys >> Edge( label='system_state', **evattr, decorate='true', fontcolor='darkgreen') >> scale
     sys >> Edge( label='update_storage', **evattr, decorate='true', fontcolor='darkgreen') >> scale
     sys >> Edge( label='actor_state', **evattr, decorate='true', fontcolor='darkgreen') >> wis
     sys >> Edge( label='system_state', **evattr, decorate='true', fontcolor='darkgreen') >> sonar
     sys >> Edge( label='update_storage', **evattr, decorate='true', fontcolor='darkgreen') >> sonar
     sys >> Edge( label='system_state', **evattr, decorate='true', fontcolor='darkgreen') >> led
     sys >> Edge( label='cmd', **evattr, decorate='true', fontcolor='darkgreen') >> msg_receiver
     sys >> Edge( label='system_state', **evattr, decorate='true', fontcolor='darkgreen') >> test_observer
     op_robot >> Edge(color='magenta', style='solid', decorate='true', label='<system_state_req<font color="darkgreen"> system_state_repl</font> &nbsp; >',  fontcolor='magenta') >> wis
     op_robot >> Edge(color='magenta', style='solid', decorate='true', label='<burn_req<font color="darkgreen"> burn_repl</font> &nbsp; >',  fontcolor='magenta') >> incinerator
     op_robot >> Edge(color='magenta', style='solid', decorate='true', label='<engage<font color="darkgreen"> engagedone engagerefused</font> &nbsp; moverobot<font color="darkgreen"> moverobotdone moverobotfailed</font> &nbsp; >',  fontcolor='magenta') >> basicrobot
     test_observer >> Edge(color='magenta', style='solid', decorate='true', label='<system_state_req<font color="darkgreen"> system_state_repl</font> &nbsp; >',  fontcolor='magenta') >> wis
     wisfacade >> Edge(color='blue', style='solid', decorate='true', label='< &harr; >',  fontcolor='blue') >> wis
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<incinerator_activation &nbsp; >',  fontcolor='blue') >> incinerator
diag
