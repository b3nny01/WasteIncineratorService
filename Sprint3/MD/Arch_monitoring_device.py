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
          sonar=Custom('sonar','./qakicons/symActorWithobjSmall.png')
          data_cleaner=Custom('data_cleaner','./qakicons/symActorWithobjSmall.png')
          led=Custom('led','./qakicons/symActorWithobjSmall.png')
          sonar_device=Custom('sonar_device','./qakicons/symActorSmall.png')
          led_device=Custom('led_device','./qakicons/symActorSmall.png')
     data_cleaner >> Edge( label='sonar_clean_data', **eventedgeattr, decorate='true', fontcolor='red') >> sonar
     sonar_device >> Edge( label='sonar_data', **eventedgeattr, decorate='true', fontcolor='red') >> data_cleaner
     sys >> Edge( label='system_state', **evattr, decorate='true', fontcolor='darkgreen') >> led
     led >> Edge(color='blue', style='solid',  decorate='true', label='<update_led_mode &nbsp; >',  fontcolor='blue') >> led_device
     sonar >> Edge(color='blue', style='solid',  decorate='true', label='<sonar_start &nbsp; >',  fontcolor='blue') >> sonar_device
diag
