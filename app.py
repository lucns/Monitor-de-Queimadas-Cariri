import time
import os
desktopPath = os.environ["HOMEPATH"] + '/Desktop'

print("start")

queimadasFolder = desktopPath + "/FiltroQueimadasEmPython"
pathIn = desktopPath + '/focos-queimadas-brasil-2011-2021.csv'
pathOut = queimadasFolder + "/ChapadaAraripeQueimadas 2011-2021.csv"

state = "CEARA"
cities = [
    "UMARI", "BAIXIO", "IPAUMIRIM", "LAVRAS DA MANGABEIRA", "GRANJEIRO", "BARRO",
    "AURORA", "MAURITI", "MILAGRES", "ABAIARA", "BREJO SANTO", "JATI", "PENA FORTE",
    "PORTEIRAS", "TARRAFAS", "ALTANEIRA", "ASSARE", "POTENGI", "ANTONIA DO NORTE",
    "ARARIPE", "SALITRE", "CAMPO SALES", "BARBALHA", "CARIRIAÇU", "CARIRIACU", "CRATO",
    "FARIAS BRITO", "JARDIM", "JUAZEIRO DO NORTE", "MISSAO VELHA", "NOVA OLINDA", "SANTANA DO CARIRI"
]

if not os.path.exists(queimadasFolder):
    os.mkdir(queimadasFolder)

originFile = open(pathIn, 'r', encoding="utf-8")
chapadaAraripeFile = open(pathOut, 'w', encoding="utf-8")
line = originFile.readline()
chapadaAraripeFile.write(line)

startTime = round(time.time() * 1000)
count = 0
for line in originFile:
    for city in cities:
        if state in line and city in line:
            count += 1
            chapadaAraripeFile.write(line)
            print(f'Running {count}')
            break
originFile.close()
chapadaAraripeFile.close()

seconds = (round(time.time() * 1000) - startTime) / 1000
print(f'{count} Linhas em {seconds} segundos.')
