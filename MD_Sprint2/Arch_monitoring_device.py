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
with Diagram('monitoring_deviceArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctx_monitoring_device', graph_attr=nodeattr):
          sonar=Custom('sonar','./qakicons/symActorSmall.png')
          datacleaner=Custom('datacleaner','./qakicons/symActorSmall.png')
          sonar_device=Custom('sonar_device','./qakicons/symActorSmall.png')
          led=Custom('led','./qakicons/symActorSmall.png')
          led_device=Custom('led_device','./qakicons/symActorSmall.png')
     with Cluster('ctx_wis', graph_attr=nodeattr):
          wis=Custom('wis(ext)','./qakicons/externalQActor.png')
     datacleaner >> Edge( label='sonardata', **eventedgeattr, decorate='true', fontcolor='red') >> sonar
     sonar_device >> Edge( label='sonardata', **eventedgeattr, decorate='true', fontcolor='red') >> datacleaner
     led >> Edge(color='blue', style='solid',  decorate='true', label='<update_led_mode &nbsp; >',  fontcolor='blue') >> led_device
     sonar >> Edge(color='blue', style='solid',  decorate='true', label='<sonarstart &nbsp; >',  fontcolor='blue') >> sonar_device
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<system_state &nbsp; >',  fontcolor='blue') >> led
diag
