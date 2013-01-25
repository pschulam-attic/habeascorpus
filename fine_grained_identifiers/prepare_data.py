#!/usr/bin/env python
'''This script prepares a preterminal data set for evaluating whether
or not including fine-grained identifiers improves the test perplexity
of n-gram models.

Using the data that has already been prepared by the CodeWalker
Eclipse application, this script will read the list of files specified
in the dataset file passed on the command line, and will produce two
files (whose paths are also specified on the command line).

The first file is a file containing a sequence of preterminals on each
line, where the kth line corresponds to the preterminal sequence of
the file listed on the kth line of the dataset file.

The second file is a file containing a sequence of preterminals in
which generic identifier tokens have been replaced with "fine grained"
identifiers. These fine grained identifiers include:

  - PACKAGE_IDENTIFIER
  - TYPE_IDENTIFIER
  - VARIABLE_IDENTIFIER
  - METHOD_IDENTIFIER
  - ANNOTATION_IDENTIFIER
  - MEMBER_VALUE_PAIR_IDENTIFIER
  - UNKNOWN_IDENTIFIER

'''

import argparse
import os

parser = argparse.ArgumentParser()
parser.add_argument('--dataset', required=True, help='Process the files on the lines of this file.')
parser.add_argument('--tokensroot', required=True, help='This is the path to the token data root.')
parser.add_argument('--identifierroot', required=True, help='This is the path to the identifier data root.')
parser.add_argument('--normaloutput', required=True, help='Write the normal preterminal sequences to this file.')
parser.add_argument('--finegrainoutput', required=True, help='Write the fine grain preterminal sequences to this file.')

def preterminals_from_token_file(filename):
    lines = (l.strip().split('\t') for l in open(filename))
    for _, preterminal in lines:
        yield preterminal

def identifiers_from_id_file(filename):
    lines = (l.strip().split('\t') for l in open(filename))
    for terminal, id_type, is_declaration in lines:
        yield id_type

def make_fine_ids(preterminals, id_types):
    id_iter = iter(id_types)
    for p in preterminals:
        if p == 'TokenNameIdentifier':
            yield next(id_iter)
        else:
            yield p

def main():
    args = parser.parse_args()

    with open(args.normaloutput, 'w') as normal, open(args.finegrainoutput, 'w') as fine:
        for filename in (l.strip() for l in open(args.dataset)):
            token_file = os.path.join(args.tokensroot, filename)
            id_file = os.path.join(args.identifierroot, filename)

            normal.write(' '.join(preterminals_from_token_file(token_file)) + '\n')
            
            preterminals = preterminals_from_token_file(token_file)
            ids = identifiers_from_id_file(id_file)
            fine.write(' '.join(make_fine_ids(preterminals, ids)) + '\n')

if __name__ == '__main__': main()
