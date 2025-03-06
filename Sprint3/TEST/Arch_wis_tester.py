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
with Diagram('wis_testerArch', show=False, outformat='png', graph_attr=graphattr) as diag:
  with Cluster('env'):
     sys = Custom('','./qakicons/system.png')
### see https://renenyffenegger.ch/notes/tools/Graphviz/attributes/label/HTML-like/index
     with Cluster('ctx_wis_tester', graph_attr=nodeattr):
          wis_observer=Custom('wis_observer','./qakicons/symActorWithobjSmall.png')
          wis_tester=Custom('wis_tester','./qakicons/symActorWithobjSmall.png')
     sys >> Edge( label='system_state', **evattr, decorate='true', fontcolor='darkgreen') >> wis_observer
     wis_observer >> Edge(color='blue', style='solid',  decorate='true', label='<check &nbsp; >',  fontcolor='blue') >> wis_tester
diag
