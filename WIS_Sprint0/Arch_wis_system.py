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
          wis=Custom('wis','./qakicons/symActorSmall.png')
          incinerator=Custom('incinerator','./qakicons/symActorSmall.png')
     with Cluster('ctx_monitoringdev', graph_attr=nodeattr):
          monitoringdevice=Custom('monitoringdevice','./qakicons/symActorSmall.png')
     incinerator >> Edge( label='endOfBurning', **eventedgeattr, decorate='true', fontcolor='red') >> wis
     monitoringdevice >> Edge( label='ashLevel', **eventedgeattr, decorate='true', fontcolor='red') >> wis
     sys >> Edge( label='endOfBurning', **evattr, decorate='true', fontcolor='darkgreen') >> wis
     incinerator >> Edge( label='endOfBurning', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     monitoringdevice >> Edge( label='ashLevel', **eventedgeattr, decorate='true', fontcolor='red') >> sys
     wis >> Edge(color='blue', style='solid',  decorate='true', label='<activationCommand &nbsp; >',  fontcolor='blue') >> incinerator
diag
