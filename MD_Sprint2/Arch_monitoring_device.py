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
          data_cleaner=Custom('data_cleaner','./qakicons/symActorSmall.png')
          led=Custom('led','./qakicons/symActorSmall.png')
          md_test_observer=Custom('md_test_observer','./qakicons/symActorSmall.png')
          sonar_device=Custom('sonar_device','./qakicons/symActorSmall.png')
          led_device=Custom('led_device','./qakicons/symActorSmall.png')
          wis=Custom('wis','./qakicons/symActorSmall.png')
     data_cleaner >> Edge( label='sonar_clean_data', **eventedgeattr, decorate='true', fontcolor='red') >> sonar
     sonar_device >> Edge( label='sonar_data', **eventedgeattr, decorate='true', fontcolor='red') >> data_cleaner
     md_test_observer >> Edge(color='magenta', style='solid', decorate='true', label='<led_device_state_req<font color="darkgreen"> led_device_state_repl</font> &nbsp; >',  fontcolor='magenta') >> led_device
     sonar >> Edge(color='blue', style='solid',  decorate='true', label='<actor_state &nbsp; >',  fontcolor='blue') >> md_test_observer
     led >> Edge(color='blue', style='solid',  decorate='true', label='<update_led_mode &nbsp; >',  fontcolor='blue') >> led_device
     sonar >> Edge(color='blue', style='solid',  decorate='true', label='<sonar_start &nbsp; >',  fontcolor='blue') >> sonar_device
     md_test_observer >> Edge(color='blue', style='solid',  decorate='true', label='<set_system_state &nbsp; >',  fontcolor='blue') >> wis
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<system_state &nbsp; >',  fontcolor='blue') >> led
     sonar >> Edge(color='blue', style='solid',  decorate='true', label='<actor_state &nbsp; >',  fontcolor='blue') >> wis
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<system_state &nbsp; >',  fontcolor='blue') >> sonar_device
diag
