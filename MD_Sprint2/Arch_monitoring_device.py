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
          monitoring_device=Custom('monitoring_device','./qakicons/symActorSmall.png')
     with Cluster('ctx_wis', graph_attr=nodeattr):
          wis=Custom('wis(ext)','./qakicons/externalQActor.png')
     sys >> Edge( label='unload_ash', **evattr, decorate='true', fontcolor='darkgreen') >> monitoring_device
diag
