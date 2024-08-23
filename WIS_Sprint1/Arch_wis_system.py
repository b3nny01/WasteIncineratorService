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
          wis=Custom('wis','./qakicons/symActorSmall.png')
          wis_state_observer=Custom('wis_state_observer','./qakicons/symActorSmall.png')
     with Cluster('ctx_basic_robot', graph_attr=nodeattr):
          robotpos=Custom('robotpos(ext)','./qakicons/externalQActor.png')
          engager=Custom('engager(ext)','./qakicons/externalQActor.png')
     sys >> Edge( label='load_rp', **evattr, decorate='true', fontcolor='darkgreen') >> scale
     sys >> Edge( label='unload_ash', **evattr, decorate='true', fontcolor='darkgreen') >> monitoring_device
     wis >> Edge( label='load_rp', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     wis >> Edge( label='unload_ash', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     wis >> Edge(color='magenta', style='solid', decorate='true', label='<conditions_verified_req<font color="darkgreen"> conditions_verified_repl</font> &nbsp; >',  fontcolor='magenta') >> wis_state_observer
     wis >> Edge(color='magenta', style='solid', decorate='true', label='<burn_req<font color="darkgreen"> burn_repl</font> &nbsp; >',  fontcolor='magenta') >> incinerator
     wis >> Edge(color='magenta', style='solid', decorate='true', label='<moverobot<font color="darkgreen"> moverobotdone moverobotfailed</font> &nbsp; >',  fontcolor='magenta') >> robotpos
     wis >> Edge(color='magenta', style='solid', decorate='true', label='<engage<font color="darkgreen"> engagedone engagerefused</font> &nbsp; >',  fontcolor='magenta') >> engager
     incinerator >> Edge(color='blue', style='solid',  decorate='true', label='<actor_state &nbsp; >',  fontcolor='blue') >> wis_state_observer
     monitoring_device >> Edge(color='blue', style='solid',  decorate='true', label='<actor_state &nbsp; >',  fontcolor='blue') >> wis_state_observer
     scale >> Edge(color='blue', style='solid',  decorate='true', label='<actor_state &nbsp; >',  fontcolor='blue') >> wis_state_observer
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<incinerator_activation &nbsp; >',  fontcolor='blue') >> incinerator
diag
